package com.mycompany.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HistogramTest {
    Histogram histogram;

    @Test
    public void generateRangers_positiveParamaters_dontThrowsException() {
        histogram = new Histogram();
        assertDoesNotThrow(() -> histogram.generateRanges(2, 3));
    }

    @Test
    public void generateRangers_negativeIntegerStepParameter_throwsException() {
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-2, 3));
    }

    @Test
    public void generateRangers_negativeIntegerAmountParameter_throwsException() {
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(2, -3));
    }

    @Test
    void firstElementIsCorrect() {
        histogram = new Histogram();
        assertTrue(new Range(1, 2).equals(histogram.generateRanges(2, 6).get(0)));
    }

    @Test
    void lastElementIsCorrect() {
        histogram = new Histogram();
        assertTrue(new Range(11, 12).equals(histogram.generateRanges(2, 6).get(5)));
    }

    @Test
    public void generate_allWordsInOneOfTheGivenRanges() {
        histogram = new Histogram();
        String test = "one qwer asdfg yx wwww";
        List<Range> list = histogram.generateRanges(2, 3);
        histogram.generate(test, list);
        Map<Range, Integer> aktual = histogram.getHistogram();
        Map<Range, Integer> expected = new HashMap<>() {{
            put(new Range(1, 2), 1);
            put(new Range(3, 4), 3);
            put(new Range(5, 6), 1);
        }};
        assertEquals(expected, aktual);
    }

    @Test
    public void generate_textWithWordsOutOfGivenRanges() {
        histogram = new Histogram();
        String test = "one qwer asdfg yx wwwwwwww";
        List<Range> list = histogram.generateRanges(2, 3);
        histogram.generate(test, list);
        Map<Range, Integer> aktual = histogram.getHistogram();
        Map<Range, Integer> expected = new HashMap<>() {{
            put(new Range(1, 2), 1);
            put(new Range(3, 4), 2);
            put(new Range(5, 6), 1);
        }};
        assertEquals(expected, aktual);
    }

    @Test
    public void generate_emptyText() {
        histogram = new Histogram();
        String list = "";
        histogram.generate(list, histogram.generateRanges(2, 3));
        assertEquals(new HashMap<Range, Integer>(), histogram.getHistogram());
    }

    @Test
    public void generate_nullText_throwsException() {
        String list = null;
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generate(list, histogram.generateRanges(2, 3)));
    }

    @Test
    public void generate_nullRange_throwsException() {
        histogram = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> histogram.generate("asad asdsd dda", null));
    }

    @Test
    public void generate_testMultipleGenerateCalls() {
        histogram = new Histogram();
        String test = "onew qwer asdfg yx wwwwwww";
        List<Range> list = histogram.generateRanges(2, 2);
        histogram.generate(test, list);


        test = "one qwer asdfg yx wwww";
        list = histogram.generateRanges(2, 3);
        histogram.generate(test, list);


        Map<Range, Integer> aktual = histogram.getHistogram();
        Map<Range, Integer> expected = new HashMap<>() {
            {
                put(new Range(1, 2), 1);
                put(new Range(3, 4), 3);
                put(new Range(5, 6), 1);
            }
        };
        assertEquals(expected, aktual);
    }

    @Test
    public void getHistogram_callingHistogramBeforeGenerateHistogram() {
        histogram = new Histogram();
        assertEquals(new HashMap<>(), histogram.getHistogram());
    }

    @Test
    public void getHistogram_callingHistogramAfterGenerateHistogram() {
        histogram = new Histogram();
        String test = "onew qwer asdfg yx wwwww";
        List<Range> list = histogram.generateRanges(2, 3);
        Map<Range, Integer> expected = new HashMap<>() {
            {
                put(new Range(1, 2), 1);
                put(new Range(3, 4), 2);
                put(new Range(5, 6), 2);
            }
        };
        histogram.generate(test, list);
        assertEquals(expected, histogram.getHistogram());
    }

    @Test
    public void generate_testCallingAfterMultipleCallsGenerateHistogram() {
        histogram = new Histogram();
        String test = "onew qwer asdfg yx wwwwwwwww";
        List<Range> list = histogram.generateRanges(2, 2);
        histogram.generate(test, list);


        test = "one qwer asdfg yx wwww";
        list = histogram.generateRanges(2, 3);
        histogram.generate(test, list);

        Map<Range, Integer> expected = new HashMap<>() {
            {
                put(new Range(1, 2), 1);
                put(new Range(3, 4), 3);
                put(new Range(5, 6), 1);
            }
        };
        assertEquals(expected, histogram.getHistogram());
    }


    @Test
    public void toString_histogramBeforeGenerate_throwException() {
        histogram = new Histogram();
        assertEquals("", histogram.toString());

    }

    @Test
    public void toString_histogramAfterGenerate_throwException() {
        histogram = new Histogram();
        String test = "one qwer asdfg yx wwww";
        List<Range> list = histogram.generateRanges(2, 3);
        histogram.generate(test, list);
        System.out.println(histogram.toString());
        assertEquals("3 - 4| ***" + System.lineSeparator() + "5 - 6| *" + System.lineSeparator() + "1 - 2| *" + System.lineSeparator(), histogram.toString());

    }


    @Test
    public void getMinValue_methodReturnWith1() {
        histogram = new Histogram();
        String test = "e er fg";
        List<Range> list = histogram.generateRanges(1, 2);
        histogram.generate(test, list);
        assertEquals(1, histogram.getMinValue());

    }

    @Test
    public void getMinValue_methodReturnWith2() {
        histogram = new Histogram();
        String test = "e a er fg";
        List<Range> list = histogram.generateRanges(1, 2);
        histogram.generate(test, list);
        assertEquals(2, histogram.getMinValue());

    }

    @Test
    public void getMaxValue_methodReturnWith3() {
        histogram = new Histogram();
        String test = "one qwer asdfg yxm wwwww";
        List<Range> list = histogram.generateRanges(2, 3);
        histogram.generate(test, list);
        assertEquals(3, histogram.getMaxValue());

    }

    @Test
    public void getMaxValue_getMax() {
        histogram = new Histogram();
        String test = "o y ww ww";
        List<Range> list = histogram.generateRanges(1, 2);
        histogram.generate(test, list);
        assertEquals(2, histogram.getMaxValue());

    }

    @Test
    void getNoMinTest() {
        histogram = new Histogram();
        histogram.generate("", List.of(new Range(1, 1), new Range(2, 2)));
        assertEquals(null, histogram.getMin());
    }

    @Test
    void getNoMaxTest() {
        histogram = new Histogram();
        histogram.generate("", List.of(new Range(1, 1), new Range(2, 2)));
        assertEquals(null, histogram.getMax());
    }


    @Test
    public void toString_afterNormalization() {
        histogram = new Histogram();
        String test = "o";
        List<Range> list = histogram.generateRanges(1, 2);
        histogram.generate(test, list);
        histogram.normalizeValues();
        StringBuilder resultText = new StringBuilder();
        resultText.append("1 - 1| ");
        for (int i = 0; i < 100; i++) {
            resultText.append("*");
        }
        resultText.append(System.lineSeparator());
        assertEquals(resultText.toString(), histogram.toString());
    }
}
