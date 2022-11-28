<%@ attribute name="petrisBoard" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.model.PetrisBoard"
 description="Petrisboard to be rendered" %>
<canvas id="canvas" width="${petrisBoard.width}" height="${petrisBoard.height}"></canvas>
<img id="source" src="${petrisBoard.background}" style="display:none">
<img id="red-bacterium" src="../resources/images/red-bacterium.png" style="display:none">
<img id="red-sarcina" src="../resources/images/red-sarcina.png" style="display:none">
<img id="blue-bacterium" src="../resources/images/blue-bacterium.png" style="display:none">
<img id="blue-sarcina" src="../resources/images/blue-sarcina.png" style="display:none">
<img id="yellow-bacterium" src="../resources/images/yellow-bacterium.png" style="display:none">
<img id="yellow-sarcina" src="../resources/images/yellow-sarcina.png" style="display:none">

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
