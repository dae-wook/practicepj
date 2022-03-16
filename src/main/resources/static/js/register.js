const successAuth = document.getElementById("successAuth");
const failureAuth = document.getElementById('failureAuth');
const defaultAuth = document.getElementById('defaultAuth');
const loading = document.getElementById('loading');
const emailAuThDiv = document.getElementById("emailAuthDiv");
const emailAuthBtn = document.getElementById('emailAuthBtn');
const authKeyInput = document.getElementById("authKeyInput");
const registerComplete = document.getElementById("registerComplete");
const emailInput = document.getElementsByName("userId")[0];
const userIdInput = document.getElementById("userIdInput");
const registerForm = document.getElementById("registerForm");


registerComplete.disabled = true;
emailAuthBtn.disabled = true;


disableBtn(emailInput,emailAuthBtn,4);

/**
 * 인증번호 확인버튼
 */
    authKeyInput.addEventListener("keyup", e => {
        const authKey = authKeyInput.value;

        if(authKeyInput.value.length > 3) {
            fetch('/member/email-auth-complete?authKey=' + authKey + "&userId=" + document.getElementsByName("userId")[0].value, {
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => response.text())
                .then(data => {
                    if (data === 'true') {
                        registerComplete.disabled = false;
                        successAuth.style.display = 'inline';
                        failureAuth.style.display = 'none';
                        defaultAuth.style.display = 'none';
                        loading.style.display = 'none';
                        let test = /*[[${test}]]*/ 'asdasda';
                        authKeyInput.setAttribute("readonly", true);
                        userIdInput.setAttribute("readonly", true);
                        document.getElementById('passwordInput').focus();
                        document.getElementById("confirmEmail").value = userIdInput.value;
                        console.log(test);
                    } else {
                        successAuth.style.display = 'none';
                        defaultAuth.style.display = 'none';
                        loading.style.display = 'none';
                        failureAuth.style.display = 'inline';
                    }
                })
        }else {
            defaultAuth.style.display = 'inline';
            loading.style.display = 'inline-block';
            successAuth.style.display = 'none';
            failureAuth.style.display = 'none';
        }
    })
/**
 * 인증메일 발송버튼
 */
emailAuthBtn.addEventListener("click", e => {
    const userId = document.getElementsByName("userId")[0].value;
    if (userId.includes("@") && userId.includes(".")) {

        emailAuThDiv.style.display = "block";
        authKeyInput.focus();
        fetch('/member/email-auth?userId=' + userId, {
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (response.status === 200) {
                } else {
                    console.log("실패")
                }
            }).catch(e => console.log(e));
    } else {
        alert("잘못된 형식.");
    }
});

/**
 * 회원가입 버튼
 */

registerComplete.addEventListener("click", ()=>{
    const userId = document.getElementsByName("userId")[0].value;
    const confirmEmail = document.getElementById("confirmEmail").value;
    if(userId !== confirmEmail) {
        alert("잘못된 접근");
        return;
    }else {
        const nameInput = document.getElementById("nameInput").value;
        const passwordInput = document.getElementById("passwordInput").value;
        const phoneInput = document.getElementById("phoneInput").value;
        let isEmpty = true;
        if(nameInput !== "" && passwordInput !== "" && phoneInput !== "" && userId !== "") {
            isEmpty = false;
        }
        if(!isEmpty){
            registerForm.action = '/member/register';
            registerForm.method = 'POST';
            registerForm.submit();
        }else {
            alert("빈 칸이 있습니다.")
        }

    }

})

function disableBtn(input, button, number) {
    input.addEventListener("keyup", e=> {
        if(input.value.length > number) button.disabled = false;
        if(input.value.length < number+1) button.disabled = true;
    })
}


