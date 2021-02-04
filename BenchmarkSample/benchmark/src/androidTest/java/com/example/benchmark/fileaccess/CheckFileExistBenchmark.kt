package com.example.benchmark.fileaccess

import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CheckFileExistBenchmark : FileAccessBenchmark() {

    override fun FileSystem.Scope.runTest() {
        val newFile = temporaryFolder.newFile()
        benchmarkRule.measureRepeated {
            newFile.exists()
        }
    }
}
