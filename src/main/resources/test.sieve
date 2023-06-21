if header :is ["x-returns-to-sender"] ["false"] {
    fileinto "Automated";
    stop;
}

# Filter emails sent to max@maxdevos.com into INBOX/Work
if address :all :is "to" "max@maxdevos.com" {
    fileinto "INBOX/Work";
}

# Filter emails sent to max@maxdevos.net into INBOX/Personal
if address :all :is "to" "max@maxdevos.net" {
    fileinto "INBOX/Personal";
}
