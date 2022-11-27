<%@ attribute name="petrisBoard" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.model.PetrisBoard"
 description="Petrisboard to be rendered" %>
<canvas id="canvas" width="${petrisBoard.width}" height="${petrisBoard.height}"></canvas>
<img id="source" src="${petrisBoard.background}" style="display:none">


<script>
function drawBoard(){ 
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var image = document.getElementById('source');
    ctx.drawImage(image, 0, 0, ${petrisBoard.width}, ${petrisBoard.height});     
    <jsp:doBody/>
}
window.onload =drawBoard();
</script>
