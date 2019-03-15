// 댓글쓰기
var socket = null;
var dealerCardLength = 0;
$("document").ready(connectWS);
function connectWS() {

    console.log("tttt")
    var ws = new WebSocket("ws://" +  document.URL.substring(7,document.URL.length-10) + "blackjackGame")
    socket = ws;

    ws.onopen = function () {
        console.log('info: connection opend')
    }

    ws.onmessage = function (event) {
        console.log('ReceiveMessage', event.data + '\n');

        var obj = JSON.parse(event.data);
        console.log(obj);

        console.log("유저출력")
        console.log(obj.pair.dealer.cards);

        var user_text = $("#game_userCard_text");
        var showUserCards = obj.pair.user.cards[0].card;
        for (var i = 1; i < obj.pair.user.cards.length; i++ ) {
            showUserCards += ", " + obj.pair.user.cards[i].card
        }
        user_text.text(showUserCards);



        var dealer_text = $("#game_dealerCard_text");
        var showDealerCards = obj.pair.dealer.cards[0].card;
        dealerCardLength = obj.pair.dealer.cards.length;
        for (var i = 1; i < dealerCardLength; i++ ) {
            showDealerCards += ", " + obj.pair.dealer.cards[i].card
            if  (i == 1) {
                dealer_text.text(obj.pair.dealer.cards[0].card + ", " + "히든카드");
                if(obj.gameResult != "DEFAULT") {
                    dealer_text.text(showDealerCards);
                }
            }

            if  (i > 1) {
                delayPrint(i, showDealerCards);
            }
        }


        if(obj.gameResult != "DEFAULT") {
            console.log(obj.gameResult);
            $('.hit-or-stand').css('display','none');
            $('#game-start-button').css('display','block');
            setTimeout(function(){
                alert(obj.gameResult);
            }, 500 * dealerCardLength);
        }

    }
}

function delayPrint(i, showDealerCards) {
    setTimeout(function() {var socketAlert = $("#game_dealerCard_text");socketAlert.text(showDealerCards);
                }, 500 *i);
}

function isDefault(gameResult) {
    if(gameResult === "") {
        console.log("not null")
        console.log(gameResult)
        return true;
        }
    return false;
}

$(".submit-write-answer button[type=submit]").on("click", submitMessage);
function submitMessage(e) {
    e.preventDefault()
    console.log("메세지")
    var queryString = $(".submit-write-answer").serialize(); //form data들을 자동으로 묶어준다.
    console.log("query : "+ queryString)
    console.log("query : "+ queryString.replace(/%/g, '%25'))
    socket.send(queryString);   //클라이언트가 서버에 메시지를 보낸다.
}


$('#game-start-button').on('click',showHitButton)
function showHitButton(e) {
    e.preventDefault()
    console.log("메세지")
    $('.hit-or-stand').css('display','block');
    $('#game-start-button').css('display','none');
    var bettingchip = prompt("배팅할 칩을 입력하세요");

    socket.send("bettingchip="+bettingchip);
    console.log(bettingchip);
}

function startGame() {

}

$('#hit-button').on('click',hit)
function hit(e) {
    socket.send("hitOrStand=hit");

}

$('#stand-button').on('click',stand)
function stand(e) {
    socket.send("hitOrStand=stand");

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