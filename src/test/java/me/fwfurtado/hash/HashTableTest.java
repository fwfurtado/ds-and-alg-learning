package me.fwfurtado.hash;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTest {

    private HashTable<Student> hashTable;

    static class Student implements Comparable<Student> {

        private Integer registrationNumber;
        private String name;

        public Student(Integer registrationNumber, String name) {
            this.registrationNumber = registrationNumber;
            this.name = name;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("registrationNumber=" + registrationNumber)
                .add("name='" + name + "'")
                .toString();
        }

        @Override
        public int compareTo(Student o) {
            return o.registrationNumber.compareTo(registrationNumber);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return registrationNumber.equals(student.registrationNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(registrationNumber);
        }
    }

    @BeforeEach
    void setup() {
        hashTable = new HashTable<>();
    }

    @Test
    void shouldInitializeAHash() {
        assertNotNull(hashTable);
    }

    @Test
    void shouldAddAStudent() {
        assertTrue(hashTable.add(new Student(1234, "Fernando")));
    }

    @Test
    void shouldNotAddDuplicatedElements() {
        assertTrue(hashTable.add(new Student(1234, "Fernando")));
        assertFalse(hashTable.add(new Student(1234, "Fernando")));
    }

    @Test
    void shouldAddElementsWithCollisionButItIsNotEquals() {
        assertTrue(hashTable.add(new Student(1234, "Fernando")));
        assertTrue(hashTable.add(new Student(13, "Fernando")));
        System.out.println(Arrays.toString(hashTable.table));
    }

    @Test
    void shouldDistributeTheElementsByKey() {
        assertTrue(hashTable.add(new Student(1234, "Fernando")));
        assertTrue(hashTable.add(new Student(321, "Willian")));
        assertTrue(hashTable.add(new Student(9, "de")));
        assertTrue(hashTable.add(new Student(78, "Souza")));
        assertTrue(hashTable.add(new Student(90879, "Furtado")));

        System.out.println(Arrays.toString(hashTable.table));
    }

    @Test
    void shouldFindAnStudentIfThisExistInHash() {
        var student = new Student(1234, "Fernando");
        assertTrue(hashTable.add(student));

        assertTrue(hashTable.contains(student));
    }

}