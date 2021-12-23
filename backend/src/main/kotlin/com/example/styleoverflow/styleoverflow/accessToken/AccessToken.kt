package com.example.styleoverflow.styleoverflow.accessToken

import com.example.styleoverflow.styleoverflow.appuser.AppUser
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime
import javax.persistence.*


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
    @SequenceGenerator(
        name = "confirmation_token_sequence",
        sequenceName = "confirmation_token_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private val id: Long? = null
}