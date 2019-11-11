package com.supersw.mybook.book.service;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.supersw.mybook.book.domain.BookSearchHistory;
import com.supersw.mybook.book.repository.BookSearchHistoryRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class BookSearchHistoryServiceTest {

    @InjectMocks
    private BookSearchHistoryService service;
    @Mock
    private BookSearchHistoryRepository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() throws Exception {
        // given
        BookSearchHistory expected = random(BookSearchHistory.class);
        given(repository.save(isA(BookSearchHistory.class))).willAnswer(a -> a.getArgument(0));

        // when
        BookSearchHistory actual = service.save(expected.getKeyword(), expected.getMemberId());

        // then
        assertThat(actual.getKeyword(), is(expected.getKeyword()));
        assertThat(actual.getMemberId(), is(expected.getMemberId()));
    }

    @Test
    public void getSearchHistories() throws Exception {
        // given
        List<BookSearchHistory> expected = randomListOf(5, BookSearchHistory.class)
            .stream()
            .sorted(Comparator.comparing(BookSearchHistory::getRegisteredDateTime).reversed())
            .collect(Collectors.toList());

        given(repository.findByMemberId("TEST")).willReturn(expected);

        // when
        List<BookSearchHistory> actual = service.getSearchHistories("TEST");

        // then
        assertThat(actual, is(expected));
        then(repository).should().findByMemberId("TEST");
    }

}
