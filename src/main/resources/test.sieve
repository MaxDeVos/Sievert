if header :contains ["x-returns-to-sender"] ["true"] {
    redirect "IMPORTANT";
} else {
    redirect "AUTOMATED";
}