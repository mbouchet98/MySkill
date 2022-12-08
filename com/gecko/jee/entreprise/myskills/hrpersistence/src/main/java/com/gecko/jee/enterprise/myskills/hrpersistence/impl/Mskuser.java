package com.gecko.jee.enterprise.myskills.hrpersistence.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the mskuser database table.
 * 
 */
@Entity
@NamedQuery(name="Mskuser.findAll", query="SELECT m FROM Mskuser m")
public class Mskuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_mskuser")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMskuser;

	private Integer active;

	private String address;

	@Column(name="creation_date")
	private Timestamp creationDate;

	private String dtype;

	@Column(name="first_name")
	private String firstName;

	@Column(name="fk_learner_preference")
	private Long fkLearnerPreference;

	@Column(name="fk_profile_id")
	private Long fkProfileId;

	@Column(name="last_name")
	private String lastName;

	@Column(name="liferay_id")
	private String liferayId;

	private String login;

	@Column(name="mail_address")
	private String mailAddress;

	private String password;

	private String phone;

	@Column(name="self_created")
	private Integer selfCreated;

	private Integer version;

	//bi-directional many-to-one association to EntretienEvaluation
	@OneToMany(mappedBy="mskuser")
	private List<EntretienEvaluation> entretienEvaluations;

	//bi-directional many-to-one association to FonctionMetier
	@ManyToOne
	@JoinColumn(name="fk_fonction_metier_id")
	private FonctionMetier fonctionMetier;

	//bi-directional many-to-many association to Formation
	@ManyToMany
	@JoinTable(
		name="mskuser_formation"
		, joinColumns={
			@JoinColumn(name="fk_mskuser_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fk_formation_id")
			}
		)
	private List<Formation> formations;

	//bi-directional many-to-many association to PlanCarriere
	@ManyToMany(mappedBy = "mskusers", cascade = CascadeType.ALL)
	private List<PlanCarriere> planCarrieres;

	public Mskuser() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param login
	 * @param mailAddress
	 */
	public Mskuser(String firstName, String lastName, String mailAddress, String liferayId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailAddress = mailAddress;
		this.liferayId = liferayId;
	}

	/**
	 * @param address
	 * @param creationDate
	 * @param firstName
	 * @param lastName
	 * @param liferayId
	 * @param login
	 * @param mailAddress
	 * @param password
	 * @param phone
	 */
	public Mskuser(String address, Timestamp creationDate, String firstName, String lastName, String liferayId,
			String login, String mailAddress, String password, String phone) {
		this.address = address;
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.liferayId = liferayId;
		this.login = login;
		this.mailAddress = mailAddress;
		this.password = password;
		this.phone = phone;
	}

	public Long getIdMskuser() {
		return this.idMskuser;
	}

	public void setIdMskuser(Long idMskuser) {
		this.idMskuser = idMskuser;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getDtype() {
		return this.dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getFkLearnerPreference() {
		return this.fkLearnerPreference;
	}

	public void setFkLearnerPreference(Long fkLearnerPreference) {
		this.fkLearnerPreference = fkLearnerPreference;
	}

	public Long getFkProfileId() {
		return this.fkProfileId;
	}

	public void setFkProfileId(Long fkProfileId) {
		this.fkProfileId = fkProfileId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLiferayId() {
		return this.liferayId;
	}

	public void setLiferayId(String liferayId) {
		this.liferayId = liferayId;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSelfCreated() {
		return this.selfCreated;
	}

	public void setSelfCreated(Integer selfCreated) {
		this.selfCreated = selfCreated;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<EntretienEvaluation> getEntretienEvaluations() {
		return this.entretienEvaluations;
	}

	public void setEntretienEvaluations(List<EntretienEvaluation> entretienEvaluations) {
		this.entretienEvaluations = entretienEvaluations;
	}

	public EntretienEvaluation addEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		getEntretienEvaluations().add(entretienEvaluation);
		entretienEvaluation.setMskuser(this);

		return entretienEvaluation;
	}

	public EntretienEvaluation removeEntretienEvaluation(EntretienEvaluation entretienEvaluation) {
		getEntretienEvaluations().remove(entretienEvaluation);
		entretienEvaluation.setMskuser(null);

		return entretienEvaluation;
	}

	public FonctionMetier getFonctionMetier() {
		return this.fonctionMetier;
	}

	public void setFonctionMetier(FonctionMetier fonctionMetier) {
		this.fonctionMetier = fonctionMetier;
	}

	public List<Formation> getFormations() {
		return this.formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public List<PlanCarriere> getPlanCarrieres() {
		return this.planCarrieres;
	}

	public void setPlanCarrieres(List<PlanCarriere> planCarrieres) {
		this.planCarrieres = planCarrieres;
	}

	@Override
	public String toString() {
		return "Mskuser [idMskuser=" + idMskuser + ", active=" + active + ", address=" + address + ", creationDate="
				+ creationDate + ", dtype=" + dtype + ", firstName=" + firstName + ", fkLearnerPreference="
				+ fkLearnerPreference + ", fkProfileId=" + fkProfileId + ", lastName=" + lastName + ", liferayId="
				+ liferayId + ", login=" + login + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", phone=" + phone + ", selfCreated=" + selfCreated + ", version=" + version
				+ ", entretienEvaluations=" + entretienEvaluations + ", fonctionMetier=" + fonctionMetier
				+ ", formations=" + formations + ", planCarrieres=" + planCarrieres + "]";
	}

}