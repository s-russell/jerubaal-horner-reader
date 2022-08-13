package com.jerubaal.reader;


import com.jerubaal.reader.ReadingPlanBuilder.Book;
import com.jerubaal.reader.ReadingPlanBuilder.Reading;
import com.jerubaal.reader.ReadingPlanBuilder.ReadingPlanItem;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ReadingPlanBuilderTest {


    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/readings.csv", numLinesToSkip=1)
    void getTodaysReadingForBook(int chapterCount, int reading, int expectedChapter) {
        var book = new ReadingPlanBuilder.Book("my book", chapterCount);
        assertEquals(expectedChapter, book.reading(reading));
    }

    @Test
    void getTodaysReadingForBookList() {
        var book1 = new Book("book1", 5);
        var book2 = new Book("book2", 3);
        var item = new ReadingPlanItem("test item", List.of(book1, book2));

        assertEquals(new Reading(book1, 1), item.reading(1));
        assertEquals(new Reading(book1, 2), item.reading(2));
        assertEquals(new Reading(book1, 3), item.reading(3));
        assertEquals(new Reading(book1, 4), item.reading(4));
        assertEquals(new Reading(book1, 5), item.reading(5));
        assertEquals(new Reading(book2, 1), item.reading(6));
        assertEquals(new Reading(book2, 2), item.reading(7));
        assertEquals(new Reading(book2, 3), item.reading(8));
        assertEquals(new Reading(book1, 1), item.reading(9));
        assertEquals(new Reading(book1, 2), item.reading(10));
        assertEquals(new Reading(book1, 3), item.reading(11));
    }
}