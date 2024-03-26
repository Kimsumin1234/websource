// 폼이 비어있는지 확인
const form = document.querySelector("form");
const name = document.getElementById("name");
const title = document.getElementById("title");
const content = document.getElementById("content");
const password = document.getElementById("password");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  if (!name.value) {
    alert("이름을 확인해 주세요");
    name.focus();
    return;
  } else if (!title.value) {
    alert("제목을 확인해 주세요");
    title.focus();
    return;
  } else if (!content.value) {
    alert("내용을 확인해 주세요");
    content.focus();
    return;
  } else if (!password.value) {
    alert("비밀번호를 확인해 주세요");
    password.focus();
    return;
  }
  form.submit();
});

document.querySelector("#list").addEventListener("click", () => {
  location.href = "/qList.do";
});
