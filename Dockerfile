FROM fpco/stack-run:lts-7.2

ADD dist .

CMD ./tw-ingestion-exe
