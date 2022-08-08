CloudZipExtractor is a utility that allows Zip files that are stored in the cloud to have their contents listed and 
files extracted without downloading the entire Zip file.  This is best suited to cloud backups snapshots like those 
provided by Backblaze.  Backblaze provide cloud backup and offer a service where a backup snapshot can be created in 
B2 storage.  The snapshot is a zip file and can be extremely large.  This software seeks to avoid excessive downloading
when only a relatively small portion of the snapshot contents need to be recovered.