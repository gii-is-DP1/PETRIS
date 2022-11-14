<!DOCTYPE html>

<html>
    <head>
        <title>Form login</title>
    <style>
* {
    margin: 0px;
    padding: 0px;
}
body{
    height: 850px;
    background-image: url('https://fondosmil.com/fondo/3585.jpg');
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;       
}
#contenedor1{
    background: #f9f7f7;
    width: 460px;
    height: 370px;
    margin: auto;
    margin-top: 100px;
}
#form1{
    width: 100%;
    padding: 50px 20px 10px 20px;
    box-sizing: border-box;

}
#contenedor1 h1{
    text-align: center;
    padding-top: 20px;
    color: #566573;
    font-size: 45px;
}
#form1 input[type="text"]{
    border: none;
    background: none;
    width: 90%;
    height: 40px;
    font-size: 15px;
    font-weight: bold;
    text-align: center;
}
#form1 input[type="submit"]{
    width: 100%;
    height: 60px;
    background: #1b4f72;
    color: white;
    cursor: pointer;
    border: 2px solid white;
    font-size: 25px;
    font-weight: 900;
}
#form1 input[type="submit"]:hover{
    background: #3498db;
}
#form1 hr{
    margin-bottom: 40px;
    color: #aed6f1;
}
img{
    float: left;
}

    </style>
    </head>
    <body>
        <div id="contenedor1">
            <h1>Join Game</h1>
            <form id="form1" name='f' action="/games/playingGame" method='POST'>
                <img src="https://www.avante.es/wp-content/uploads/2018/03/471a1ad342659289433e05a611d206f8.png" width="40px" height="38px" alt=""/>
                <input type="text" name="username" placeholder="Enter opponent's name"/>
                <img src="https://www.avante.es/wp-content/uploads/2018/03/471a1ad342659289433e05a611d206f8.png" width="40px" height="45px" alt=""/>
                <input type="text" name="username" placeholder="Enter your name"/>
                <input type="submit" name="submit" value="Join">
                <hr>
            </form>
        </div>
    </body>
</html>