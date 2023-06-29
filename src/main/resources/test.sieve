addflag "\\FLAGGED";

# Exemption for anything related to Craigslist
if address :all :contains ["To", "From"] "craigslist"
{
    fileinto "INBOX/Personal";
    stop;
}

# File emails that don't return to sender or come from addresses containing "noreply" into Automated
if anyof(
        header :is ["x-returns-to-sender"] ["false"],
        address :all :contains "from" "noreply"
        )
{
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

