package com.example.benchmark.fileaccess

import androidx.benchmark.junit4.measureRepeated


class CreateFileBenchmark : FileAccessBenchmark() {

    override fun FileSystem.Scope.runTest() {
        benchmarkRule.measureRepeated {
            temporaryFolder.newFile()
        }
    }
}
