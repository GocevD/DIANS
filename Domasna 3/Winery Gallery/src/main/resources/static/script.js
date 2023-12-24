    function LoginPopup(){
    var element = document.getElementById("login");
    element.style.display = (element.style.display === "flex") ? "none" : "flex";
}

    document.addEventListener('DOMContentLoaded', function () {
    var aboutUsLink = document.querySelector('.aboutus');

    aboutUsLink.addEventListener('click', function () {

    var container = document.querySelector('#ourstory');
    container.scrollIntoView({ behavior: 'smooth' });

});
});

    function Login() {
    var loginForm = document.querySelector('.loginform');
    var loginpopup = document.getElementById('login');

    if(loginForm.style.display === "flex")
    loginForm.style.display = "none";

    else
    loginForm.style.display = "flex";
    loginForm.style.position = "fixed";
    loginpopup.style.display = "none";
    loginForm.style.zIndex = "999";
}


        function goToLink() {
        // Get the dynamic link set by Thymeleaf
        var dynamicLink = document.getElementById('dynamicLink').getAttribute('href');

        // Redirect to the dynamic link
        window.location.href = dynamicLink;
    }