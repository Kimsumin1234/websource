// 페이지 나누기 개념 추가
const actionForm = document.querySelector("#actionForm");
const element = `<input type="hidden" name="bno" value="${bno}">`;
actionForm.insertAdjacentHTML("beforeend", element);

document.querySelector("#list").addEventListener("click", () => {
  // location.href = "/qList.do";

  // 페이지 나누기 개념 추가후 경로
  // 이거는 bno 를 보낼 필요가 없음 => bno 삭제
  actionForm.querySelector('[name="bno"]').remove();
  actionForm.submit();
});
document.querySelector("#modify").addEventListener("click", () => {
  // location.href = "/qModify.do?bno=" + bno;

  // 페이지 나누기 개념 추가후 경로
  actionForm.action = "/qModify.do";
  actionForm.submit();
});
document.querySelector("#reply").addEventListener("click", () => {
  // location.href = "/qReplyView.do?bno=" + bno;

  actionForm.action = "/qReplyView.do";
  actionForm.submit();
});
document.querySelector("#delete").addEventListener("click", () => {
  // location.href = "/view/qna_board_pwdCheck.jsp?bno=" + bno;

  actionForm.action = "/view/qna_board_pwdCheck.jsp";
  actionForm.submit();
});
