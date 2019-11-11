package com.supersw.mybook.book;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.supersw.mybook.book.domain.BookSearchHistory;
import com.supersw.mybook.book.domain.BookSearchHistorySummary;
import com.supersw.mybook.book.service.BookSearchHistoryService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookSearchHistoryRestControllerTest {

    @InjectMocks
    private BookSearchHistoryRestController controller;
    @Mock
    private BookSearchHistoryService service;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get() {
        // given
        String memberId = random(String.class);
        List<BookSearchHistory> expected = randomListOf(10, BookSearchHistory.class);
        given(service.getSearchHistories(memberId)).willReturn(expected);

        // when
        List<BookSearchHistory> actual = controller.get(memberId);

        // then
        assertThat(actual, is(expected));
        then(service).should().getSearchHistories(memberId);
    }

    @Test
    public void getSummary() {
        // given
        List<BookSearchHistorySummary> expected = randomListOf(100, BookSearchHistorySummary.class);
        given(service.getSummary()).willReturn(expected);

        // when
        List<BookSearchHistorySummary> actual = controller.getSummary();

        // then
        assertThat(actual, is(expected));
        then(service).should().getSummary();
    }

}
