/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


if (window.addEventListener) { //for W3C DOM
    window.addEventListener("load", init, false);
} else if (window.attachEvent) { //for IE
    window.attachEvent("onload", init);
} else {
    window.onload = init;
}

function init() {
    //起動時は グループ部分は非表示
    //きどうじは グループぶぶんは非表示
    // hide the group section at start-up
    notShowAllData();
}

function notShowAllData() {
    document.getElementById("form:centerPanel").style.display = "none";
    document.getElementById("form:addRow").style.display = "none";
}

function showFileSelection() {
    document.getElementById("form:centerPanel").style.display = "block";
    document.getElementById("form:pList").style.display = "block";
    document.getElementById("form:workSheet").style.display = "none";
    document.getElementById("form:sheetValue").style.display = "none";
    document.getElementById("form:tableLists").style.display = "none";
    document.getElementById("form:addRow").style.display = "none";
    document.getElementById("form:addRowToTable").style.display = "none";    
}

function showWorkSheetSelection() {
    document.getElementById("form:centerPanel").style.display = "block";
    document.getElementById("form:pList").style.display = "block";
    document.getElementById("form:workSheet").style.display = "block";
    document.getElementById("form:sheetValue").style.display = "none";
    document.getElementById("form:tableLists").style.display = "none";
    document.getElementById("form:addRow").style.display = "none";
    document.getElementById("form:addRowToTable").style.display = "none";    
}

function showContents() {
    document.getElementById("form:centerPanel").style.display = "block";
    document.getElementById("form:pList").style.display = "block";
    document.getElementById("form:workSheet").style.display = "block";
    document.getElementById("form:sheetValue").style.display = "block";
    document.getElementById("form:tableLists").style.display = "block";
    document.getElementById("form:addRow").style.display = "none";
    document.getElementById("form:addRowToTable").style.display = "none";    
}

function showAddRowMenu() {
    document.getElementById("form:centerPanel").style.display = "block";
    document.getElementById("form:pList").style.display = "block";
    document.getElementById("form:workSheet").style.display = "block";
    document.getElementById("form:sheetValue").style.display = "block";
    document.getElementById("form:tableLists").style.display = "block";
    document.getElementById("form:addRow").style.display = "block";
    document.getElementById("form:addRowToTable").style.display = "none";    
}

function showAddRowContents() {
    document.getElementById("form:centerPanel").style.display = "block";
    document.getElementById("form:pList").style.display = "none";
    document.getElementById("form:workSheet").style.display = "none";
    document.getElementById("form:sheetValue").style.display = "none";
    document.getElementById("form:tableLists").style.display = "none";
    document.getElementById("form:addRow").style.display = "none";
    document.getElementById("form:addRowToTable").style.display = "block";    
}
