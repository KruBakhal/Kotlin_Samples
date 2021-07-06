package com.example.kotlin_samples

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.lay_comon_sign_process.view.*
import java.util.*


//TODO("MainActivity")
class MainActivity : AppCompatActivity() {

    var isLoggedIn: Boolean = false
    var accessToken: AccessToken? = null;
    var callbackManager: CallbackManager? = null
    val RC_Google_SIGN_IN: Int = 123
    var account: GoogleSignInAccount? = null
    var context: Context? = null;
    var mGoogleSignInClient: GoogleSignInClient? = null
    val facebook: LoginButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this;

        googleSetupProcess();

        facebookSetupProcess()

    }

    private fun facebookSetupProcess() {
        fb_process.txtStatus.text = "Status"
        fb_process.g_login.text = "FaceBook Login"
        fb_process.g_logout.text = "FaceBook LogOut"
        FacebookSdk.sdkInitialize(this.applicationContext)

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("Success", "Login")
                    isLoggedIn = true
                    val profile: Profile = Profile.getCurrentProfile();
                    fb_process.txtStatus.text = profile.name
                    Log.d("TAG", "onSuccess: " + profile.toString())
                }

                override fun onCancel() {
                    Toast.makeText(this@MainActivity, "Login Cancel", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: FacebookException) {
                    Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_LONG).show()
                }
            })


        accessToken = AccessToken.getCurrentAccessToken()
        isLoggedIn = accessToken != null && !accessToken!!.isExpired

        fb_process.g_login.setOnClickListener {

            LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("public_profile"));

        }
        fb_process.g_logout.setOnClickListener {
            LoginManager.getInstance().logOut();
            fb_process.txtStatus.text = "Facebook Login"

        }


    }


    private fun googleSetupProcess() {
        g_process.txtStatus.text = "Status"
        g_process.g_login.text = "Google Login"
        g_process.g_logout.text = "Google LogOut"

        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_Google_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_Google_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    private fun updateUI(acct: GoogleSignInAccount?) {
        if (acct != null) {
            val personName: String = acct.getDisplayName()!!
            val personGivenName: String = acct.getGivenName()!!
            val personFamilyName: String = acct.getFamilyName()!!
            val personEmail: String = acct.getEmail()!!
            val personId: String = acct.getId()!!
            val personPhoto: Uri = acct.getPhotoUrl()!!
            g_process.txtStatus.text = personEmail
            Log.d(
                "TAG",
                "updateUI:{$personName} {$personGivenName} {$personFamilyName} {$personEmail} {$personId} {$personPhoto}"
            )
            Toast.makeText(context, "{$personEmail}", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickLogIn(view: View) {
        signIn()
    }

    fun onClickLogOut(view: View) {

        val result = mGoogleSignInClient?.signOut()
        g_process.txtStatus.text = "Google Login "

    }

    fun onClickStart(view: View) {
        startActivity(Intent(context, MoreAppActivity::class.java))

    }


}