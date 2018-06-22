package harry.test.entity;

/**
 * 
 * @author harry
 *
 */
public class Jar {
	private String groupId;
	private String artifact;
	private String version;
	public Jar(String groupId, String artifact, String version) {
		super();
		this.groupId = groupId;
		this.artifact = artifact;
		this.version = version;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifact() {
		return artifact;
	}
	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Jar [groupId=" + groupId + ", artifact=" + artifact + ", version=" + version + "]";
	}
}
