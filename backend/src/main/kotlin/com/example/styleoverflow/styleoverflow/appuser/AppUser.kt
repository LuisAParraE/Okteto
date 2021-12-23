package com.example.styleoverflow.styleoverflow.appuser

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.transaction.TransactionUsageException
import java.time.LocalDate
import javax.persistence.*

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
class AppUser(
    private var name: String,
    private var surname: String,
    @Column(
        name = "username",
        unique = true,
        nullable = false
    )
    private var username: String,
    @Column(
        name = "email",
        nullable = true
    )
    private var email: String,
    private var password: String,
    private var birthdate: LocalDate,
    @Enumerated(EnumType.STRING)
    private var appUserRole: AppUserRole,
    private var sessions : Int = 0,
    private var locked: Boolean? = null,
    private var enable: Boolean? = null
) : UserDetails {

    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private var id: Long? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val simpleGrantedAuthority = SimpleGrantedAuthority(appUserRole.name)
        return mutableListOf(simpleGrantedAuthority)
    }

    override fun getPassword(): String {
        return password
    }

    fun setPassword(passwordIn: String) {
        password = passwordIn
    }

    override fun getUsername(): String {
        return username
    }

    fun setUsername(usernameN: String) {
        username = usernameN
    }

    fun getName(): String {
        return name
    }

    fun getSurname(): String {
        return surname
    }

    fun getBirthdate(): LocalDate {
        return birthdate
    }

    fun getSessions() : Int {
        return sessions
    }

    fun setSessions(status: Int) {
        sessions = status
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !locked!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enable!!
    }
}
