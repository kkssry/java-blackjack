// 댓글쓰기
var socket = null;

$("document").ready(connectWS);
function connectWS() {

    console.log("tttt")

    var ws = new WebSocket("ws://localhost:8080/blackjackGame")
    socket = ws;

    ws.onopen = function () {
        console.log('info: connection opend')
    }

    ws.onmessage = function (event) {
        console.log('ReceiveMessage', event.data + '\n');
        var socketAlert = $("#game_userCard_text");

        console.log(socketAlert);
        socketAlert.append(event.data + '<br>');

    }
}

$(".submit-write-answer button[type=submit]").on("click", submitMessage);

function submitMessage(e) {
    e.preventDefault()
    console.log("메세지")
    var queryString = $(".submit-write-answer").serialize(); //form data들을 자동으로 묶어준다.
    console.log("query : "+ queryString)
    console.log("query : "+ queryString.replace(/%/g, '%25'))
    socket.send(queryString);

}


$('#milestone-menu').on('click',showHitButton)

function showHitButton(e) {
    e.preventDefault()

    console.log("메세지")
    $('.hit-or-stand').css('display','block');
    $('#milestone-menu').css('display','none');
}

function startGame() {

}


function userChoiceHitOrStand() {


}

$('#label-menu').on('click',hit)
function hit(e) {
    var abc = $("#game_dealerCard_text");
    abc.text("카드 1");

}

function whenUserTurnFinish() {

}

String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) {
      return typeof args[number] != 'undefined'
          ? args[number]
          : match
          ;
    });
  };