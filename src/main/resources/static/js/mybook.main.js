(function () {

      $('#join-btn').click(function () {
        var username = $("input[name=username]").val();
        if (!username) {
          alert("아이디를 입력해주세요.");
          return;
        }
        var password = $("input[name=password]").val();
        if (!username) {
          alert("패스워드를 입력해주세요.");
          return;
        }
        var data = {
          username: username,
          password: password
        };

        $.ajax({
          url: location.protocol + '//' + location.host + '/api/members',
          method: 'POST',
          contentType: 'application/json',
          data: JSON.stringify(data),
          success: function (response) {
            alert("회원가입 성공!");
            window.location.reload();
          },
          error: function (result) {
            if (result.responseJSON.userMessage) {
              alert(result.responseJSON.userMessage);
            } else {
              alert("서버 오류입니다.\n잠시 후 다시 시도해주세요.");
            }
          }
        });

      });

      $('#login-btn').click(function () {
        var username = $("input[name=username]").val();
        if (!username) {
          alert("아이디를 입력해주세요.");
          return;
        }
        var password = $("input[name=password]").val();
        if (!username) {
          alert("패스워드를 입력해주세요.");
          return;
        }
        var data = {
          username: username,
          password: password
        };

        $.ajax({
          url: location.protocol + '//' + location.host + '/api/members/login',
          method: 'POST',
          contentType: 'application/json',
          data: JSON.stringify(data),
          success: function (response) {
            alert("로그인 성공!");
            window.location.reload();
          },
          error: function (result) {
            if (result.responseJSON.userMessage) {
              alert(result.responseJSON.userMessage);
            } else {
              alert("서버 오류입니다.\n잠시 후 다시 시도해주세요.");
            }
          }
        });
      });

      $('#search-btn').click(function () {
        var query = $("input[name=bookName]").val();
        if (!query) {
          alert("검색할 책 제목을 입력해주세요.");
          return;
        }
        searchBook(query, 1);
      });

      $('#search-hist-btn').click(function () {
        var $searchHistList = $('.search-hist-list');
        if ($searchHistList.is(":visible")) {
          $searchHistList.hide();
          $searchHistList.children().remove();
          return;
        } else {
          $searchHistList.show();
        }

        var memberId = $("input[name=memberId]").val();
        $.ajax({
          url: location.protocol + '//' + location.host + '/api/book-search-histories?memberId=' + memberId,
          method: 'GET',
          success: function (response) {
            if ($.isEmptyObject(response)) {
              $('.search-hist-list').append('<strong">검색 내역이 없습니다.</strong>');
              return;
            }
            $.each(response, function (i, hist) {
              appendHistToList(hist);
            });

          },
          error: function (result) {
            if (result.responseJSON.userMessage) {
              alert(result.responseJSON.userMessage);
            } else {
              alert("서버 오류입니다.\n잠시 후 다시 시도해주세요.");
            }
          }
        });
      });

      $('#popular-keyword-btn').click(function () {
        var popularKeywordList = $('.popular-keyword-list');
        if (popularKeywordList.is(":visible")) {
          popularKeywordList.hide();
          popularKeywordList.children().remove();
          return;
        } else {
          popularKeywordList.show();
        }

        $.ajax({
          url: location.protocol + '//' + location.host + '/api/book-search-histories/summary',
          method: 'GET',
          success: function (response) {
            if ($.isEmptyObject(response)) {
              $('.search-hist-list').append('<strong">아직 검색한 내역이 없습니다.</strong>');
              return;
            }
            $.each(response, function (i, summary) {
              appendSummaryToList(summary);
            });

          },
          error: function (result) {
            if (result.responseJSON.userMessage) {
              alert(result.responseJSON.userMessage);
            } else {
              alert("서버 오류입니다.\n잠시 후 다시 시도해주세요.");
            }
          }
        });
      });

      $(document).on("click", '#next-page-btn', function () {
        var nextPageNum = parseInt($("input[name=pageNum]").val()) + 1;
        $("input[name=pageNum]").val(nextPageNum);
        searchBook($("input[name=query]").val(), nextPageNum);
      });

      $(document).on("click", '#prev-page-btn', function () {
        var prevPageNum = parseInt($("input[name=pageNum]").val()) - 1;
        $("input[name=pageNum]").val(prevPageNum);
        searchBook($("input[name=query]").val(), prevPageNum);
      });

      $(document).on("click", '.book-title', function () {
        var $bookInfo = $(this).parent().find('.book-info');
        if ($bookInfo.is(":visible")) {
          $bookInfo.hide();
        } else {
          $bookInfo.show();
        }
      });

      function searchBook(query, page) {
        $.ajax({
          url: location.protocol + '//' + location.host + '/api/books?query=' + query + '&page=' + page,
          method: 'GET',
          success: function (response) {
            $('.book-list').children().remove();
            if ($.isEmptyObject(response.books)) {
              $('.book-list').append('<strong">검색 결과가 없습니다.</strong>');
              return;
            }
            $.each(response.books, function (i, book) {
              appendBookToList(book);
            });

            if (page === 0 && $('.book-list').has('#prev-page-btn').length > 0) {
              $('#prev-page-btn').remove();
            } else if (page > 1) {
              $('.book-list').append('<strong id="prev-page-btn" style="color: red">이전 페이지</strong>\t');
            }

            if (response.page.isEnd) {
              $('#next-page-btn').remove();
            } else if ($('.book-list').has('#next-page-btn').length === 0) {
              $('.book-list').append('<strong id="next-page-btn" style="color: darkblue">다음 페이지</strong>');
            }

            $('.book-list').append("<input type='hidden' name='pageNum' value='" + page + "'/>");
            $('.book-list').append("<input type='hidden' name='query' value='" + query + "'/>");

          },
          error: function (result) {
            if (result.responseJSON.userMessage) {
              alert(result.responseJSON.userMessage);
            } else {
              alert("서버 오류입니다.\n잠시 후 다시 시도해주세요.");
            }
          }
        });
      }

      function appendSummaryToList(summary) {
        $('.popular-keyword-list').append(
            '<li>\n'
            + '            <strong class="keyword-name">' + summary.keyword + '</strong>\n'
            + '            <strong class="hist-datetime">' + summary.searchCount + '</strong>\n'
            + '          </li>'
        );
      }

      function appendHistToList(hist) {
        $('.search-hist-list').append(
            '<li>\n'
            + '            <strong class="hist-keyword">' + hist.keyword + '</strong>\n'
            + '            <strong class="hist-datetime">' + hist.registeredDateTime + '</strong>\n'
            + '          </li>'
        );
      }

      function appendBookToList(book) {
        $('.book-list').append(
            '<li>\n'
            + '            <strong class="book-title">' + book.title + '</strong>\n'
            + '            <div class="book-info" style="display: none">\n'
            + '              <dl>'
            + '                 <img src="' + book.thumbnail + '">\n'
            + '                <dd>' + book.contents + '</dd>\n'
            + '              </dl>\n'
            + '              <dl>\n'
            + '                <dt><span>저자</span></dt>\n'
            + '                <dd>' + book.authors + '</dd>\n'
            + '                <dt><span">ISBN</span></dt>\n'
            + '                <dd>' + book.isbn + '</dd>\n'
            + '                <dt><span>출판사</span></dt>\n'
            + '                <dd>' + book.publisher + '</dd>\n'
            + '                <dt><span>출판일</span></dt>\n'
            + '                <dd>' + book.datetime + '</dd>\n'
            + '                <dt><span>정가</span></dt>\n'
            + '                <dd>' + book.price + '</dd>\n'
            + '                <dt><span>판매가</span></dt>\n'
            + '                <dd>' + book.salePrice + '</dd>\n'
            + '              </dl>\n'
            + '            </div>\n'
            + '          </li>'
        );
      }

    }
)();