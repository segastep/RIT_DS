package com.ritds.test;

import com.ritds.Storage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StorageTest {
    private Storage st;

    @BeforeEach
    void setUp() {
        st = new Storage();
    }

    @AfterEach
    void tearDown() {
        st = null;
    }

    @Test
    void testCreateSimple() {
        assertEquals("OK 1.5", st.create(0, new Long(100), "1.5"));
    }

    @Test
    void testUpdateSimple() {
        st.create(0, new Long(100), "1.5");
        assertEquals("OK 1.5", st.update(0, new Long(100), "1.5"));
    }

    @Test
    void testGetSimple() {
        st.create(0, new Long(100), "1.0");
        assertEquals("OK 1.0", st.get(0, new Long(100)));
    }

    @Test
    void testLatestOutOfTwoEqualTS() {
        st.create(0, new Long(100), "1.0");
        st.update(0, new Long(100), "1.1");
        assertEquals("OK 1.1", st.get(0, new Long(100)));
    }

    @Test
    void testLatestOutOfThreeDiffTS() {
        st.create(0, new Long(100), "1.0");
        st.update(0, new Long(105), "1.1");
        st.update(0, new Long(102), "2.0");
        assertEquals("OK 105 1.1", st.latest(0));
    }

    @Test
    void testLatestErr() {
        List<String> expected = Arrays.asList("ERR No history exists for identifier '1'");
        List<String> actual = Arrays.asList(st.latest(1));
        assertLinesMatch(expected, actual);
    }

    @Test
    void testSimpleDelete(){
        st.create(1, new Long(100), "2.0");
        assertEquals("OK 2.0",st.delete(1));
        assertFalse(st.returnMap().containsKey(1));
    }

    @Test
    void testDeleteWithTS(){
        st.create(1, new Long(100), "2.2");
        st.update(1, new Long(102), "2.5");
        st.update(1, new Long(105), "3.0");
        st.update(1, new Long(99), "1.0");
        st.update(1, new Long(108), "3.5");
        assertEquals("OK 2.5", st.delete(1, new Long(102)));
        assertEquals(2, st.returnMap().get(1).size());
    }

    @Test
    void testDeleteThrowError(){
        st.create(1, new Long(100), "2.2");
        assertEquals("ERR no history exists for identifier '1'", st.delete(1, new Long(100)));
        assertEquals(0, st.returnMap().get(1).size());
    }

    @Test
    void TestDeleteNonExistingID(){
        assertEquals("ERR no such identifier '234321423'",st.delete(234321423));
    }

    @Test
    void TestDeleteNonExistingIDwithTS()
    {
        assertEquals("ERR no such identifier '234321423'",st.delete(234321423, new Long(10000)));
    }


}
