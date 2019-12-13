package me.fwfurtado.hash;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.RunnerException;

public class HashBenchmark {

    private static final Random RANDOM = new SecureRandom();

    @State(Scope.Thread)
    public static class BenchmarkState {

        private HashTable<Integer> hashTable;

        @Setup(Level.Trial)
        public void setup() {
            hashTable = new HashTable<>();
        }

        HashTable<Integer> getHashTable() {
            return hashTable;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void addBenchmark(BenchmarkState state) {

        var hash = state.getHashTable();

        RANDOM.ints().limit(11)
            .forEach(hash::add);

    }

    public static void main(String[] args) throws IOException, RunnerException {
        Main.main(args);
    }
}
