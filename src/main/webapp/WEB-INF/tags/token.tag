<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the token to show" %>
 <%@ attribute name="token" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.token.Token"
 description="Token to be rendered" %>
 
 image = document.getElementById('${token.colour.name}-${token.tokenType.name}');
 
 ctx.drawImage(image,${token.getXPosition()},${token.getYPosition()},58,58);
