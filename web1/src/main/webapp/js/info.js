// submit 클릭시
// 값이 비어 있지않도록 처리
// age는 숫자인지 확인

// js, css 파일은 정적인 파일이다, 인식이 늦을수있다
// 패키징이 잘 됬는지 f12 눌러서 Source 창에 js 폴더가 제대로 들어왔는지 확인해보기
// 제대로 인식이 안된다면 새로고침 우클릭 해서 강력새로고침, 캐시비우기 새로고침 해보기

const form = document.querySelector("form");
const id = document.getElementById("id");
const name = document.getElementById("name");
const age = document.getElementById("age");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  if (id.value == "") {
    alert("id 입력");
    id.focus();
    return;
  }
  if (name.value == "") {
    alert("name 입력");
    name.focus();
    return;
  }
  if (age.value == "" || isNaN(age.value)) {
    alert("age 입력");
    age.focus();
    return;
  }

  // 검증 완료 후 폼 submit 실행
  form.submit();
});
