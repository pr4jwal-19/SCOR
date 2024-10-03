
console.log("Script Loaded");

// initial theme
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    // initially -> change theme
    changeTheme(currentTheme);
});

// TODO:
function changeTheme() {

    // currentTheme is a global variable
    // this will apply the current theme to all the elements
    document.querySelector("html").classList.add(currentTheme);

    // set the listener for the button
    const changeThemeBtn = document.querySelector("#theme-change-btn")
    changeThemeBtn.addEventListener("click", (event) => {
        // remove the current theme
        document.querySelector("html").classList.remove(currentTheme);

        // change the theme
        currentTheme = currentTheme === "light" ? "dark" : "light";

        // apply the new theme
        document.querySelector("html").classList.add(currentTheme);

        // set the theme to local storage
        setTheme(currentTheme);
    })
}

// Set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}
// Get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}
