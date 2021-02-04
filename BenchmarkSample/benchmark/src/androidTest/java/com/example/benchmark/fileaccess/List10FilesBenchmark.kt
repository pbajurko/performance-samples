package com.example.benchmark.fileaccess

import androidx.benchmark.junit4.measureRepeated

class List10FilesBenchmark : FileAccessBenchmark() {

    override fun FileSystem.Scope.runTest() {
        for (i in 1..10) temporaryFolder.newFile()
        benchmarkRule.measureRepeated {
            temporaryFolder.root.listFiles()
        }
    }
}
