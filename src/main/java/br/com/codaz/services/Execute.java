package br.com.codaz.services;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.IGClient.Builder.LoginHandler;
import com.github.instagram4j.instagram4j.utils.IGChallengeUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import org.jinstagram.Instagram;
import org.jinstagram.InstagramClient;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.likes.LikesFeed;
import org.jinstagram.entity.media.MediaInfoFeed;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.entity.users.feed.UserFeedData;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.entity.comments.MediaCommentResponse;
import org.jinstagram.entity.comments.MediaCommentsFeed;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.likes.LikesFeed;
import org.jinstagram.entity.locations.LocationInfo;
import org.jinstagram.entity.locations.LocationSearchFeed;
import org.jinstagram.entity.media.MediaInfoFeed;
import org.jinstagram.entity.relationships.RelationshipFeed;
import org.jinstagram.entity.tags.TagInfoFeed;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.tags.TagSearchFeed;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.model.Relationship;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.brunocvcunha.instagram4j.requests.InstagramAutoCompleteUserListRequest;
import org.brunocvcunha.instagram4j.requests.InstagramFollowRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetInboxRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetRecentActivityRequest;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
import org.brunocvcunha.instagram4j.requests.InstagramLoginRequest;
import org.brunocvcunha.instagram4j.requests.InstagramPostCommentRequest;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTimelineFeedRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.internal.InstagramFetchHeadersRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginPayload;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.util.InstagramGenericUtil;
import org.brunocvcunha.instagram4j.util.InstagramHashUtil;
import org.brunocvcunha.inutils4j.MyNumberUtils;


public class Execute {
	
	
	static UserInfo userInfo = null;
	static String userID= null;
	static InstagramClient instagram = null;
	
	
        
	


public static void conectarSeguir (String usuario, String senha, String pagina, int quantidade, int espera) throws InstagramException, IOException{
    espera = espera*1000;
    
    try {       
Instagram4j instagram = Instagram4j.builder().username(usuario).password(senha).build();
instagram.setup();
instagram.login();
InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(pagina));
int i = 0;
InstagramGetUserFollowersResult githubFollowers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));

List<InstagramUserSummary> users = githubFollowers.getUsers();
for (InstagramUserSummary user : users) {
    System.out.println("User " + user.getUsername() + " follows "+pagina);
    instagram.sendRequest(new InstagramFollowRequest(userResult.getUser().getPk()));
    userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(user.getUsername()));
    //instagram.sendRequest(new InstagramFollowRequest(userResult.getUser().getPk()));
    try {
   Thread.sleep(espera);
i++;
if(i==quantidade){
i=0;
JOptionPane.showMessageDialog(null, "Finalizado!");
break;
}
} catch (Exception e) {
   e.printStackTrace();
   JOptionPane.showMessageDialog(null, "Ops, algo deu errado!");
}

    }
	
	} catch (Exception e) {
        System.out.println("Ops, algo deu errado!");
        
        }
}
	



public static void postFoto(String usuario, String senha, String caminho, String desc) throws InstagramException, IOException{
     
    
  try {       
Instagram4j instagram = Instagram4j.builder().username(usuario).password(senha).build();
instagram.setup();
instagram.login();
JOptionPane.showMessageDialog(null, "Logado com sucesso! Escolha a imagem para ser publicada.");
instagram.sendRequest(new InstagramUploadPhotoRequest(
        new File(caminho),
        desc));

	
	} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, algo deu errado!");
        
        }
}


//SEGUIR PESSOAS COM CONTAS PREDEFINIDAS
//SENHA PREDEFINIDA
public static void seguir(String userS) throws IOException, InterruptedException{
String senha="rexona123";
String usuario[] = new String[7];
usuario[0]="iasmynoliver98";
usuario[1]="joassist56";
//usuario[2]="ofdrert95";
usuario[2]="lidaneves879";
usuario[3]="almeidabass_da";
//usuario[4]="tedrick.makeen";
//usuario[5]="yashua.gurnoor";

for(int i = 0; i<6; i++){    
Instagram4j instagram = Instagram4j.builder().username(usuario[i]).password(senha).build();
instagram.setup();
instagram.login();

InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userS));
System.out.println("ID is " + userResult.getUser().getPk());

instagram.sendRequest(new InstagramFollowRequest(userResult.getUser().getPk()));



InstagramFeedResult UserFeed = instagram.sendRequest(new InstagramUserFeedRequest(1));
for (InstagramFeedItem feedResult : UserFeed.getItems()) {
    System.out.println("Post ID: " + feedResult.getPk());
    
instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
}


Thread.sleep(5000);
}



}


public static void curtirTag(String usuario, String senha, String tag, int quantidade, int espera) throws IOException, InterruptedException{
espera = espera*1000;
    
    Instagram4j instagram = Instagram4j.builder().username(usuario).password(senha).build();
    instagram.setup();
    instagram.login();
    JOptionPane.showMessageDialog(null, "Logado com sucesso!");
    int i = 0;
    InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest(tag));
for (InstagramFeedItem feedResult : tagFeed.getItems()) {
    System.out.println("Post ID: " + feedResult.getPk());
    //like
    instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
    //comentarios
    //instagram.sendRequest(new InstagramPostCommentRequest(feedResult.getPk(), comentario));
    
  try {
      System.out.println("Intervalo");
   Thread.sleep(espera);
i++;
if(i==quantidade){
i=0;
JOptionPane.showMessageDialog(null, "Finalizado!");
break;
}
} catch (Exception e) {
   e.printStackTrace();
   JOptionPane.showMessageDialog(null, "Ops, algo deu errado!");
}
    
}
    
}


public static void comentarTag(String usuario, String senha, String tag, int quantidade, int espera, String comentario) throws IOException, InterruptedException{
espera = espera*1000;
    
    Instagram4j instagram = Instagram4j.builder().username(usuario).password(senha).build();
    instagram.setup();
    instagram.login();
    JOptionPane.showMessageDialog(null,"Logado com sucesso!");
    int i = 0;
    InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest(tag));
for (InstagramFeedItem feedResult : tagFeed.getItems()) {
    System.out.println("Post ID: " + feedResult.getPk());
    //like
    instagram.sendRequest(new InstagramLikeRequest(feedResult.getPk()));
    //comentarios
    instagram.sendRequest(new InstagramPostCommentRequest(feedResult.getPk(), comentario));
    
  try {
      System.out.println("Intervalo");
   Thread.sleep(espera);
i++;
if(i==quantidade){
i=0;
JOptionPane.showMessageDialog(null, "Finalizado!");
break;
}
} catch (Exception e) {
   e.printStackTrace();
   JOptionPane.showMessageDialog(null, "Ops, algo deu errado!");
}
    
}
    
}


public static void main(String[] args) throws IOException, InterruptedException{
//DESTINADO A TESTES DIRETOS
//seguir("");
}


}