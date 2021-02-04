package com.example.benchmark.fileaccess

import androidx.benchmark.junit4.measureRepeated

class WriteToFileBenchmark : FileAccessBenchmark() {

    override fun FileSystem.Scope.runTest() {
        val newFile = temporaryFolder.newFile()
        benchmarkRule.measureRepeated {
            newFile.writeText("This is a test")
        }
    }
}
