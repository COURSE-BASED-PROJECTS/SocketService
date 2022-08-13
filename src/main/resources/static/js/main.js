// 'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var driverIDInput = document.querySelector('#driver');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');


var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function openConnectionGPS(event) {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnectedGPS, onError);
}


function onConnected(options) {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}

function onConnectedGPS() {
    // Subscribe to the Public Topic
    alert("Connect Successfully!!!")
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.subscribe('/topic/1', onMessageReceivedTest);
}

function onMessageReceivedTest(payload){
    const message = JSON.parse(payload.body)
    console.log(message)
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function sendMessageGPS() {
    var driverID = driverIDInput.value.trim();
    const pos = [{
        latitude: 10.825005,
        longitude: 106.760088
    },{
        latitude: 10.786818,
        longitude: 106.749161
    }
    ]

    if(driverID && stompClient) {
        var GPS = {
            driverIdentification: "12345678",
            driverID,
            latitude: pos[(Math.floor(Math.random() * 2))].latitude + '',
            longitude:pos[(Math.floor(Math.random() * 2))].longitude + '',
            type: "GPS"
        };
        stompClient.send("/app/gps.getGps", {}, JSON.stringify(GPS));
        driverIDInput.value = '';
    }
}

function order() {
    if(stompClient) {
        var order = {
            idHailing: (Math.floor(Math.random() * 41) + 10) +'',
            idDriver: null,
            idClient: (Math.floor(Math.random() * 41) + 10) +'',
            hailing: {
                locationStart:{
                    latitude: 10.7628356,
                    longitude: 106.6802937,
                    name: "start",
                },
                locationEnd:{
                    latitude: 10.8507214,
                    longitude: 106.7697336,
                    name: "end",
                },
                distance: 3000,
                timeDuring: 10000,
                timeStart: null,
                cost: 0,
                carType: 2
            },
            status: "test",
            scope: []
        };
        stompClient.send("/app/order.getOrder", {}, JSON.stringify(order));
        driverIDInput.value = '';
    }
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    alert('111')
    console.log(message);
    //
    // var messageElement = document.createElement('li');
    //
    // if(message.type === 'JOIN') {
    //     messageElement.classList.add('event-message');
    //     message.content = message.sender + ' joined!';
    // } else if (message.type === 'LEAVE') {
    //     messageElement.classList.add('event-message');
    //     message.content = message.sender + ' left!';
    // } else {
    //     messageElement.classList.add('chat-message');
    //
    //     var avatarElement = document.createElement('i');
    //     var avatarText = document.createTextNode(message.sender[0]);
    //     avatarElement.appendChild(avatarText);
    //     avatarElement.style['background-color'] = getAvatarColor(message.sender);
    //
    //     messageElement.appendChild(avatarElement);
    //
    //     var usernameElement = document.createElement('span');
    //     var usernameText = document.createTextNode(message.sender);
    //     usernameElement.appendChild(usernameText);
    //     messageElement.appendChild(usernameElement);
    // }
    //
    // var textElement = document.createElement('p');
    // var messageText = document.createTextNode(message.content);
    // textElement.appendChild(messageText);
    //
    // messageElement.appendChild(textElement);
    //
    // messageArea.appendChild(messageElement);
    // messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)

document.getElementById("connectGPS").addEventListener("click", openConnectionGPS);
document.getElementById("sendGPS").addEventListener("click", sendMessageGPS);
document.getElementById("order").addEventListener("click", order);