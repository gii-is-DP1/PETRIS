
<layout pageName="home">
    <div class="inicio">
        <html>
            <head>
            <meta charset="utf-8">
            <title>Play Game</title>
            <style> 
               body
               {
                height: 400px;
                background-image: url('https://fondosmil.com/fondo/3585.jpg');
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
            
                
               }
               h1
               {
                color: black;
                text-align: center;
                font-family: "Franchise";
                font-size: 80px;
               }
               button
               {
                display: inline-block;
                padding: 15px 25px;
                font-size: 24px;
                cursor: pointer;
                text-align: center;	
                text-decoration: none;
                outline: none;
                color: #fff;
                background-color: #4CAF50;
                border: none;
                border-radius: 15px;
                box-shadow: 0 9px #999;
                
               }

               button:hover {background-color: #3e8e41}

               button:active 
               {
                background-color: #3e8e41;
                box-shadow: 0 5px #666;
                transform: translateY(8px);
               }
               h2{
                color: rgb(184, 0, 0);
                font-family: "Algerian";
                font-size: 60px;

               }    
               
            </style>   
            </head>
            <body>

                <form action="/games/create"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 65%;">Crear partida</button></form>
                <form action="/games/join"><button class="button" style="height: 60px; width: 500px; position: absolute; right: 36%; bottom: 55%;">Unirse</button></form>
                <img src="https://2tomatoesgames.com/wp-content/uploads/2020/07/petris-logo.png" style="height: 150px; position: absolute; right: 35%; bottom: 80%;">


            </body>
            
            
        </html>
      </div>

      <div class = "espacio">



      </div>

</layout>