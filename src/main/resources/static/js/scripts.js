// 댓글쓰기
var socket = null;

$("#peter100").on("click", connectWS);
function connectWS(e) {

    e.preventDefault();
    console.log("tttt")

    var ws = new WebSocket("ws://192.168.0.3:8080/blackjackGame")
    socket = ws;

    ws.onopen = function () {
        console.log('info: connection opend')
    }

    ws.onmessage = function (event) {
        console.log('ReceiveMessage', event.data + '\n');
        var socketAlert = $("#game_comment_text");

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



String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function(match, number) {
      return typeof args[number] != 'undefined'
          ? args[number]
          : match
          ;
    });
  };