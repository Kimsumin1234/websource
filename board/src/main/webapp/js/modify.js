document.querySelector(".btn-danger").addEventListener("click", () => {
  location.href = "/qList.do";
});

const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", () => {
  actionForm.submit();
});
