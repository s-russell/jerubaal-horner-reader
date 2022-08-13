package com.jerubaal.reader;

import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ReadingPlanBuilder {

    public record Book(String name, int chapterCount) {
        public int reading(int ordinal) {
            if (ordinal < 1) {
                throw new IllegalArgumentException("reading must have value 1 or greater.");
            }
            int index = ordinal - 1;
            return (index % chapterCount) + 1;
        }
    }


    public record Reading(Book book, int chapterNumber) {
    }

    public record ReadingPlanItem(String categoryName, List<Book> books) {
        public Reading reading(int readingNumber) {
            var readingList = books.stream()
                    .flatMap(book -> IntStream.range(1, book.chapterCount + 1).boxed().map(chap -> new Reading(book, chap)))
                    .collect(toList());
            return readingList.get((readingNumber - 1) % readingList.size());
        }
    }

    public record ReadingPlan(List<ReadingPlanItem> items) {
        List<Reading> reading(int readingNumber) {
            return items.stream().map(item -> item.reading(readingNumber)).collect(toList());
        }
    }

    @Produces
    public ReadingPlan getReadingPlan() {
        return new ReadingPlan(
                List.of(
                        new ReadingPlanItem("Pentateuch", List.of(
                                new Book("Genesis", 50),
                                new Book("Exodus", 40),
                                new Book("Leviticus", 27),
                                new Book("Numbers", 36),
                                new Book("Deuteronomy", 34)
                        )),

                        new ReadingPlanItem("Writings", List.of(
                                new Book("Joshua", 24),
                                new Book("Judges", 21),
                                new Book("Ruth", 4),
                                new Book("I Samuel", 31),
                                new Book("II Samuel", 24),
                                new Book("I Kings", 22),
                                new Book("II Kings", 25),
                                new Book("I Chronicles", 29),
                                new Book("II Chronicles", 36),
                                new Book("Ezra", 10),
                                new Book("Nehemiah", 13),
                                new Book("Esther", 10)
                        )),

                        new ReadingPlanItem("Psalms", List.of(
                                new Book("Psalms", 150)
                        )),

                        new ReadingPlanItem("Proverbs", List.of(
                                new Book("Proverbs", 31)
                        )),

                        new ReadingPlanItem("Wisdom", List.of(
                                new Book("Job", 42),
                                new Book("Ecclesiastes", 12),
                                new Book("Song of Songs", 8)
                        )),

                        new ReadingPlanItem("Prophets", List.of(
                                new Book("Isaiah", 66),
                                new Book("Jeremiah", 52),
                                new Book("Lamentations", 5),
                                new Book("Ezekiel", 48),
                                new Book("Daniel", 12),
                                new Book("Hosea", 14),
                                new Book("Joel", 3),
                                new Book("Amos", 9),
                                new Book("Obadiah", 1),
                                new Book("Jonah", 4),
                                new Book("Micah", 7),
                                new Book("Nahum", 3),
                                new Book("Habakkuk", 3),
                                new Book("Zephaniah", 3),
                                new Book("Haggai", 2),
                                new Book("Zechariah", 14),
                                new Book("Malachi", 4)
                        )),

                        new ReadingPlanItem("Gospels", List.of(
                                new Book("Matthew", 28),
                                new Book("Mark", 16),
                                new Book("Luke", 24),
                                new Book("John", 21)
                        )),

                        new ReadingPlanItem("Acts", List.of(
                                new Book("Acts", 28)
                        )),

                        new ReadingPlanItem("Epistles A", List.of(
                                new Book("Romans", 16),
                                new Book("I Corinthians", 16),
                                new Book("II Corinthians", 13),
                                new Book("Galations", 6),
                                new Book("Ephesians", 6),
                                new Book("Philippians", 4),
                                new Book("Colossians", 4),
                                new Book("Hebrews", 13)
                        )),

                        new ReadingPlanItem("Epistles B", List.of(
                                new Book("I Thessalonians", 5),
                                new Book("II Thessalonians", 3),
                                new Book("I Timothy", 6),
                                new Book("II Timothy", 4),
                                new Book("Titus", 3),
                                new Book("Philemon", 1),
                                new Book("James", 5),
                                new Book("I Peter", 5),
                                new Book("II Peter", 3),
                                new Book("I John", 5),
                                new Book("II John", 1),
                                new Book("III John", 1),
                                new Book("Jude", 1),
                                new Book("Revelation", 22)
                        ))
                ));
    }
}