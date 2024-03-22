// submit 발생시
// submit 기능 중지
// criteria,keyword 비어 있는지 확인
// 비어있으면 alert
// 값이 존재한다면 submit
const form = document.querySelector("form");
const criteria = document.getElementById("criteria");
const keyword = document.getElementById("keyword");
console.log(criteria.value);

form.addEventListener("submit", (e) => {
  e.preventDefault();
  if (criteria.value == "검색 조건 선택") {
    alert("검색 조건을 선택해주세요");
    criteria.focus();
    return;
  } else if (!keyword.value) {
    alert("검색어가 비어있습니다");
    keyword.focus();
    return;
  }
  e.target.submit();
});
