// submit 발생시
// submit 기능 중지
// code,price 비어 있는지 확인
// code 는 값이 있다면 네자리 인지도 확인
// 비어있으면 alert
// 값이 존재한다면 submit
const form = document.querySelector("form");
const code = document.getElementById("code");
const price = document.getElementById("price");
console.log(form);

form.addEventListener("submit", (e) => {
  e.preventDefault();
  if (!code.value || code.value.length != 4 || isNaN(code.value)) {
    alert("code가 비어있거나 숫자 4자리가 아닙니다");
    code.focus();
    return;
  } else if (!price.value || isNaN(price.value)) {
    alert("price가 비어있거나 숫자가 아닙니다");
    price.focus();
    return;
  }
  e.target.submit();
});
