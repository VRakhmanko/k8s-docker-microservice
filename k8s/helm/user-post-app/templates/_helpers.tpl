{{/*
Create chart version.
*/}}
{{- define "user-post-app.version" -}}
{{- printf "%s" .Chart.Version }}
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
{{- define "project.namespace" -}}
{{- default .Release.Namespace .Values.namespaceName  -}}
{{- end -}}