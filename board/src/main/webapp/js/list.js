// (1)
// page 영역 가져오기
const pagination = document.querySelector(".pagination");
const actionForm = document.querySelector("#actionForm");

// (2)
// 새글 작성 클릭시 a 태그 기능 중지
// actionForm 안에 page=1, criteria="", keyword="" 변경 후
// actionForm submit();
// actionForm.action = "/view/qna_board_write.jsp"
const btn = document.querySelector(".btn-success");

btn.addEventListener("click", (e) => {
  e.preventDefault();
  actionForm.querySelector("[name='page']").value = 1;
  actionForm.querySelector("[name='criteria']").value = "";
  actionForm.querySelector("[name='keyword']").value = "";

  actionForm.action = "/view/qna_board_write.jsp";
  actionForm.submit();
});

// (2)
// 제목 클릭 시 a 태그 기능중지
// href 가져오기
// actionForm 안에 bno 태그 추가
// actionForm.action = "/qCount.do"
const moves = document.querySelectorAll(".move");

moves.forEach((move) => {
  move.addEventListener("click", (e) => {
    e.preventDefault();
    const href = e.target.getAttribute("href");

    const element = `<input type="hidden" name="bno" value="${href}">`;
    actionForm.insertAdjacentHTML("beforeend", element);

    actionForm.action = "/qCount.do";

    // console.log(actionForm);
    actionForm.submit();
  });
});

// (3)
// 하단의 페이지 번호를 클릭 시 a 태그 기능을 일단 중지
// 가져온 속성 값을 actionForm 의 page value 값으로 지정하기
pagination.addEventListener("click", (e) => {
  e.preventDefault();

  // 태그 속성값 가져오기 (다른 태그는 1,2번방법 값이 같은데 href 만 조금 다르다)
  // 1번 방법 : http://localhost:8080/3
  console.log(e.target.href);
  // 2번 방법 : 3
  console.log(e.target.getAttribute("href"));

  // a 태그의 href 속성 값을 가져오기
  const href = e.target.getAttribute("href");

  actionForm.querySelector("[name='page']").value = href;

  // console.log(actionForm);
  actionForm.submit();
});

// (4)
// 사용자가 목록 개수 변경 시 목록값 가져온 후
// actionForm 의 amount 값 변경 후 actionForm 전송
const amount = document.querySelector("select[name='amount']");
console.log(amount);
amount.addEventListener("change", (e) => {
  const changeAmount = e.target.value;
  actionForm.querySelector("[name='amount']").value = changeAmount;
  actionForm.submit();
});

// (5)
// 검색
const criteria = document.querySelector("[name='criteria']");
const keyword = document.querySelector("[name='keyword']");
// 검색한후 1페이지를 보여줘야 결과를 잘 확인할수있다
document.querySelector("[name='page']").value = 1;

search.addEventListener("submit", (e) => {
  e.preventDefault();

  if (criteria.value == "n") {
    alert("검색조건을 선택하세요");
    criteria.focus();
    return;
  } else if (!keyword.value) {
    alert("검색어를 입력하세요");
    keyword.focus();
    return;
  }

  e.target.submit();
});
