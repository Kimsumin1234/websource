// 세션값 저장 클릭시 sessionSet.jsp 이동
// 세션값 삭제 클릭시 sessionDel.jsp 이동
// 세션값 초기화 클릭시

const btn1 = document.querySelector("div button:nth-child(1)");
const btn2 = document.querySelector("div button:nth-child(2)");
const btn3 = document.querySelector("div button:nth-child(3)");
console.log(btn1);
console.log(btn2);
console.log(btn3);
btn1.addEventListener("click", () => {
  // 자바스크립트로 페이지 이동 방법
  location.href = "sessionSet.jsp";
});
btn2.addEventListener("click", () => {
  location.href = "sessionDel.jsp";
});
btn3.addEventListener("click", () => {
  location.href = "sessionInv.jsp";
});
