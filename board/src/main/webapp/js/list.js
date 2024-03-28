// page 영역 가져오기
const pagination = document.querySelector(".pagination");
console.log(pagination);

// 검색
const search = document.querySelector("[name='search']");
const criteria = document.querySelector("[name='criteria']");
const keyword = document.querySelector("[name='keyword']");

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
