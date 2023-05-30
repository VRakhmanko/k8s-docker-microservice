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