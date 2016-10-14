module Lib
    ( startApp
    ) where

import System.Exit

startApp :: IO ()
startApp = do putStrLn "lib yo"
              exitSuccess
