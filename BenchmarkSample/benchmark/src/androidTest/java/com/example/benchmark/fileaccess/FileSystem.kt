package com.example.benchmark.fileaccess

import android.content.Context
import android.os.Environment
import org.junit.Assert
import org.junit.rules.TemporaryFolder

class FileSystem(private val appContext: Context) {
    inner class Scope(val temporaryFolder: TemporaryFolder) {
    }

    fun runOnInternal(block: Scope.() -> Unit) {
        val scope = Scope(TemporaryFolder(appContext.filesDir))
        runInScope(scope, block)
    }

    fun runOnExternalEmulated(block: Scope.() -> Unit) {
        val externalEmulated = appContext.getExternalFilesDirs(null).firstOrNull {
            !Environment.isExternalStorageRemovable(it) && Environment.isExternalStorageEmulated(it)
        }
        Assert.assertNotNull("Device is missing external emulated storage", externalEmulated)
        val scope = Scope(TemporaryFolder(externalEmulated))
        runInScope(scope, block)
    }

    fun runOnExternalRemovable(block: Scope.() -> Unit) {
        val externalRemovable = appContext.getExternalFilesDirs(null).firstOrNull { Environment.isExternalStorageRemovable(it) }
        Assert.assertNotNull("Device is missing external removable storage", externalRemovable)
        val scope = Scope(TemporaryFolder(externalRemovable))
        runInScope(scope, block)
    }

    private inline fun runInScope(scope: Scope, block: Scope.() -> Unit) {
        scope.temporaryFolder.create()
        block(scope)
        scope.temporaryFolder.delete()
    }

}