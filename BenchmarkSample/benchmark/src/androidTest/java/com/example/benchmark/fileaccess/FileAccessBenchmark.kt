package com.example.benchmark.fileaccess

import android.content.Context
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class FileAccessBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val fileSystem = FileSystem(context)

    @Test
    fun runOnInternal() = fileSystem.runOnInternal {
        runTest()
    }

    @Test
    fun runOnExternalRemovable() = fileSystem.runOnExternalRemovable {
        runTest()
    }

    @Test
    fun runOnExternalEmulated() = fileSystem.runOnExternalEmulated {
        runTest()
    }

    abstract fun FileSystem.Scope.runTest()
}
