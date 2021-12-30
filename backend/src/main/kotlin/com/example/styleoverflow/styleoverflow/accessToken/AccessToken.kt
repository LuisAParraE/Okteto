package com.example.styleoverflow.styleoverflow.accessToken

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Class to represent a session token for maintaining a login session.
 * @param sessionId : Id of a login session.
 * @param createdAt : Date and time of creation of the token.
 * @param expiresAt : Date and time of expiration of the token.
 * @param active : Boolean representing whether a token is active or not.
 * @param appUser : An existing user fo
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
class AccessToken (
    @Column(nullable = false) val sessionId: String,
    @Column(nullable = false) val createdAt: LocalDateTime,
    @Column(nullable = false) val expiresAt: LocalDateTime,
    @Column(nullable = false) var active : Boolean  = true,
    @JoinColumn(nullable = false, name = "app_user_id") @ManyToOne val appUser: AppUser
) {
    // Id basically only used for access token to have a primary key
    @SequenceGenerator(
        name = "confirmation_token_sequence",
        sequenceName = "confirmation_token_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private val id: Long? = null
}