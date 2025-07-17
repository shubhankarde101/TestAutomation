package com.automation.JiraManagementPojo;

import java.util.List;

public class IssueTypeBugPojo {

	private Field fields;

	
	public Field getFields() {
		return fields;
	}

	public void setFields(Field fields) {
		this.fields = fields;
	}

	public class Field {

		private Project project;

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public IssueType getIssuetype() {
			return issuetype;
		}

		public void setIssuetype(IssueType issuetype) {
			this.issuetype = issuetype;
		}

		public List<String> getLabels() {
			return labels;
		}

		public void setLabels(List<String> labels) {
			this.labels = labels;
		}

		public Assignee getAssignee() {
			return assignee;
		}

		public void setAssignee(Assignee assignee) {
			this.assignee = assignee;
		}

		private String summary;
		private String description;
		private IssueType issuetype;
		private List<String> labels;
		private Assignee assignee;

	}

	public class Project {
		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
	}

	public class IssueType {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public class Assignee {
		private String assignee;

		public String getAssignee() {
			return assignee;
		}

		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}
	}

}