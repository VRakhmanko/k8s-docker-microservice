{{/*
Create chart version.
*/}}
{{- define "user-post-app.version" -}}
{{- .Chart.Version }}
{{- end }}

{{/*
Create chart current date.
*/}}
{{- define "user-post-app.current-date" -}}
{{- now|date ("2006-01-02") }}
{{- end }}

{{/*
Expand the namespace of the chart.
*/}}
{{- define "k8s-docker-microservice.namespace" -}}
{{- default .Release.Namespace .Values.namespace -}}
{{- end -}}