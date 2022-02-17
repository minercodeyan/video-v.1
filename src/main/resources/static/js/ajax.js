const xhttp = new XMLHttpRequest();
try {
    console.log("lol");
    let sendEmailBtn = document.querySelector("#confirmBtn");
    sendEmailBtn.addEventListener("click", function (e) {
        e.preventDefault();
        xhttp.open("POST", "/ajax/send");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        let email = document.querySelector("#email");
        const parameters = "email=" + email.value;

        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {

            }
        }
        xhttp.send(parameters);
    });
} catch (e) {
}