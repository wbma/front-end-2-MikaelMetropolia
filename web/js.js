const startBox2 = document.getElementById("start-box-2");
const startBox1 = document.getElementById("start-box-1");
const button1 = document.getElementById("sheet-1");
const button2 = document.getElementById("sheet-2");
const button = document.getElementById("piano");



button.onclick = function () {
    if (startBox2.className !== "piano"){
        startBox2.className = "piano";
        startBox1.innerHTML = "Piano keyboard";
        startBox2.innerHTML = " Something here. <br> Div id = start-box-2 <br>Viewing piano NOW";
    }
};


button1.onclick = function () {
    if (startBox2.className !== "sheet-1"){
        startBox2.className = "sheet-1";
        startBox1.innerHTML = "Music sheet 1";
        startBox2.innerHTML = " Something here. <br> Div id = start-box-2 <br>Viewing sheet 1 NOW";
    }
};


button2.onclick = function () {
    if (startBox2.className !== "sheet-2"){
        startBox2.className = "sheet-2";
        startBox1.innerHTML = "Music sheet 2";
        startBox2.innerHTML = " Something here. <br> Div id = start-box-2 <br> Viewing sheet 2 NOW";
    }
};