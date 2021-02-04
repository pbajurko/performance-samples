package com.example.benchmark.fileaccess

import androidx.benchmark.junit4.measureRepeated

class ReadFromFileBenchmark : FileAccessBenchmark() {

    override fun FileSystem.Scope.runTest() {
        val newFile = temporaryFolder.newFile()
        newFile.writeText("This is a test")
        benchmarkRule.measureRepeated {
            newFile.readBytes()
        }
    }
}
